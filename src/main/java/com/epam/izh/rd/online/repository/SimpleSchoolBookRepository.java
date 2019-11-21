package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        if (book != null) {
            SchoolBook[] buffer = new SchoolBook[schoolBooks.length + 1];
            System.arraycopy(schoolBooks, 0, buffer, 0, schoolBooks.length);
            buffer[schoolBooks.length] = book;
            schoolBooks = new SchoolBook[schoolBooks.length + 1];
            System.arraycopy(buffer, 0, schoolBooks, 0, schoolBooks.length);
            return true;
        }
        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] foundSchoolBooks = new SchoolBook[schoolBooks.length];
        int foundCounter = 0;
        for (SchoolBook sBook : schoolBooks) {
            if (sBook.getName().equalsIgnoreCase(name)) {
                foundSchoolBooks[foundCounter] = sBook;
                foundCounter++;
            }
        }
        SchoolBook[] result = new SchoolBook[foundCounter];
        System.arraycopy(foundSchoolBooks, 0, result, 0, foundCounter);
        return foundCounter > 0 ? result : new SchoolBook[0];
    }

    @Override
    public boolean removeByName(String name) {
        SchoolBook[] sBooksToRemain = new SchoolBook[schoolBooks.length];
        int initialLength = schoolBooks.length;
        int remainCounter = 0;
        for (SchoolBook sBook : schoolBooks) {
            if (!sBook.getName().equalsIgnoreCase(name)) {
                sBooksToRemain[remainCounter] = sBook;
                remainCounter++;
            }
        }
        schoolBooks = new SchoolBook[remainCounter];
        System.arraycopy(sBooksToRemain, 0, schoolBooks, 0, remainCounter);
        return initialLength != remainCounter;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
