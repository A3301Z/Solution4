package org.example.sql_queries;

public enum UserCrudQueries {
    SAVE("insert into users (id, username) values (?, ?)"),
    FIND_ALL("select * from users"),
    FIND_BY_ID("select * from users where id = ?"),
    DELETE("delete from users where id = ?");

    private final String query;

    UserCrudQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
