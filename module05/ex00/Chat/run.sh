#!/bin/bash

# Variables (these are now expected to be set as environment variables)
CONTAINER_NAME="my-postgres-container"
POSTGRES_PASSWORD=trevor123@
POSTGRES_USER=trevor
POSTGRES_DB=testDB
SQL_FILE_1="schema.sql"
SQL_FILE_2="data.sql"
HOST_SQL_DIR="./src/main/resources/"  # Directory on the host containing the SQL files
CONTAINER_SQL_DIR="/"  # Directory inside the container to place the SQL files

# Check if the PostgreSQL image is already pulled
if [[ "$(docker images -q postgres:latest 2> /dev/null)" == "" ]]; then
  echo "PostgreSQL Docker image not found. Pulling image..."
  docker pull postgres
else
  echo "PostgreSQL Docker image already exists."
fi

# Check if the PostgreSQL container is already running
if [[ "$(docker ps -q -f name=$CONTAINER_NAME)" ]]; then
    echo "Container $CONTAINER_NAME is already running."
else
    if [[ "$(docker ps -aq -f status=exited -f name=$CONTAINER_NAME)" ]]; then
        # Cleanup old container
        echo "Removing old container with name $CONTAINER_NAME..."
        docker rm $CONTAINER_NAME
    fi

    # Run the PostgreSQL container
    echo "Running PostgreSQL container..."
    docker run --name $CONTAINER_NAME -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD -e POSTGRES_USER=$POSTGRES_USER -e POSTGRES_DB=$POSTGRES_DB -d -p 5432:5432 postgres

    # Wait for a few seconds to ensure the container is up and running
    sleep 5
fi

# Copy SQL files into the container
echo "Copying SQL files into the container..."
docker cp $HOST_SQL_DIR/$SQL_FILE_1 $CONTAINER_NAME:$CONTAINER_SQL_DIR/$SQL_FILE_1

docker cp $HOST_SQL_DIR/$SQL_FILE_2 $CONTAINER_NAME:$CONTAINER_SQL_DIR/$SQL_FILE_2

# Execute the SQL files
echo "Executing SQL files..."
docker exec -i $CONTAINER_NAME psql -U $POSTGRES_USER -d $POSTGRES_DB -f $CONTAINER_SQL_DIR/$SQL_FILE_1
sleep 5
docker exec -i $CONTAINER_NAME psql -U $POSTGRES_USER -d $POSTGRES_DB -f $CONTAINER_SQL_DIR/$SQL_FILE_2

# Verify execution
echo "Verifying execution..."
docker exec -it $CONTAINER_NAME psql -U $POSTGRES_USER -d $POSTGRES_DB -c "\dt"

echo "PostgreSQL setup and SQL file execution completed successfully!"



# Run the Java application
echo "Running the Java application..."
mvn clean package
java -jar target/chatApp00-1.0.0.jar

# Cleanup
# echo "Cleaning up..."
# docker stop $CONTAINER_NAME
# docker rm $CONTAINER_NAME

