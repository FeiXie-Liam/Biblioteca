package com.thoughtworks.biblioteca.utils;

public enum Options {
    LIST_ALL_BOOKS{
        @Override
        public String toString() {
            return String.valueOf(super.ordinal());
        }
    },
    RETURN_BOOK{
        @Override
        public String toString() {
            return String.valueOf(super.ordinal());
        }
    },
    QUIT{
        @Override
        public String toString() {
            return String.valueOf(super.ordinal());
        }
    }
}