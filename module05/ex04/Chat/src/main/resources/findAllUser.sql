WITH userData AS (
    SELECT
        u.id AS userid,
        u.login AS login,
        u.password AS password,

        -- Chatrooms the user is part of (so)
        array_agg(DISTINCT so.id) FILTER (WHERE so.id IS NOT NULL) AS so_ids,
        array_agg(so.roomName) FILTER (WHERE so.roomName IS NOT NULL) AS so_names,
        array_agg(jsonb_build_object('id', so_owner.id, 'login', so_owner.login, 'password', so_owner.password))
        FILTER (WHERE so_owner.id IS NOT NULL) AS so_owners,

        -- Chatrooms owned by the user (cr)
        array_agg(DISTINCT cr.id) FILTER (WHERE cr.id IS NOT NULL) AS cr_ids,
        array_agg(cr.roomName) FILTER (WHERE cr.roomName IS NOT NULL) AS cr_names,
        array_agg(jsonb_build_object('id', cr_owner.id, 'login', cr_owner.login, 'password', cr_owner.password))
        FILTER (WHERE cr_owner.id IS NOT NULL) AS cr_owners

    FROM
        "User" u
            LEFT JOIN "User_Chatroom" uc ON u.id = uc.userid
            LEFT JOIN "Chatroom" so ON uc.chatroomid = so.id -- Chatrooms the user is part of
            LEFT JOIN "User" so_owner ON so.owner = so_owner.id -- Join to get owner details for so
            LEFT JOIN "Chatroom" cr ON cr.owner = u.id -- Chatrooms owned by the user
            LEFT JOIN "User" cr_owner ON cr.owner = cr_owner.id -- Join to get owner details for cr
    GROUP BY
        u.id, u.login, u.password
)
SELECT *
FROM userData
ORDER BY userid
OFFSET ? LIMIT ?;