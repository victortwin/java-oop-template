package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {

        if (findByFullName(author.getName(), author.getLastName()) == null) {
            Author[] buffer = new Author[authors.length];
            System.arraycopy(authors, 0, buffer, 0, authors.length);
            authors = new Author[authors.length + 1];
            System.arraycopy(buffer, 0, authors, 0, buffer.length);
            authors[authors.length - 1] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(name) && author.getLastName().equalsIgnoreCase(lastname)) {
                return author;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        for (int i = 0; i < authors.length; i++) {
            if (authors[i].getName().equalsIgnoreCase(author.getName()) &&
                    authors[i].getLastName().equalsIgnoreCase(author.getLastName())) {
                Author[] buffer = new Author[authors.length - 1];
                System.arraycopy(authors, 0, buffer, 0, i);
                for (int j = i + 1; j < authors.length ; j++) {
                    buffer[j - 1] = authors[j];
                }
                authors = new Author[authors.length - 1];
                System.arraycopy(buffer, 0, authors, 0, buffer.length);
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
