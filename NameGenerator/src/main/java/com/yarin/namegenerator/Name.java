package com.yarin.namegenerator;

public class Name {
    private String FirstChar;
    private String content;
    private String category;
    private int id;

    // private constructor to prevent direct instantiation
    private Name() {}

    // Getter methods

    public String getFirstChar() {
        return FirstChar;
    }

    public String getContent() {
        return content;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }



    // Builder class
    public static class Builder {
        private String FirstChar;
        private String content;
        private String category;
        private int id;

        public Builder() {}

        public String getFirstChar() {
            return FirstChar;
        }

        public String getContent() {
            return content;
        }

        public String getCategory() {
            return category;
        }

        public int getId() {
            return id;
        }

        public Builder setFirstChar(String firsChar) {
            this.FirstChar = firsChar;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Name build() {
            Name name = new Name();
            name.FirstChar = this.FirstChar;
            name.content = this.content;
            name.category = this.category;
            name.id = this.id;
            return name;
        }
    }
}
