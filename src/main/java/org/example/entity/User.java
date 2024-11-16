package org.example.entity;

import java.util.UUID;

public class User {

    private UUID id;

    private String username;

    private User(builder userBuilder) {
        this.id = userBuilder.id;
        this.username = userBuilder.username;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public String toString() {
        return "id: " + id + "\nusername: " + username;
    }

    public static class builder {

         private UUID id;
         private String username;

         public builder id(UUID id) {
             this.id = id;
             return this;
         }

        public builder username(String username) {
            this.username = username;
            return this;
        }

        public User build() {
             return new User(this);
        }
    }
}
