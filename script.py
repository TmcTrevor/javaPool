import os

def replace_package_name(directory, old_package, new_package):
    # Iterate over all files in the directory
    for filename in os.listdir(directory):
        if filename.endswith(".java"):
            filepath = os.path.join(directory, filename)

            # Read the content of the file
            with open(filepath, 'r') as file:
                content = file.readlines()

            # Replace the package name
            with open(filepath, 'w') as file:
                for line in content:
                    if line.startswith("package " + old_package):
                        line = line.replace(old_package, new_package)
                    file.write(line)
            print(f"Updated package in {filename}")

# Usage example:
directory = "module01/ex05"
old_package = "ex04"
new_package = "ex05"
replace_package_name(directory, old_package, new_package)
