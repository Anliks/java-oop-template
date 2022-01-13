package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        SchoolBook[] newSchoolBook = Arrays.copyOf(schoolBooks, schoolBooks.length+1);
        newSchoolBook[newSchoolBook.length-1] = book;
        schoolBooks = Arrays.copyOf(newSchoolBook, newSchoolBook.length);
        for(SchoolBook sb : schoolBooks){
            if(sb.equals(book)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int plus = 0;
        SchoolBook[] newSchoolBooks = new SchoolBook[schoolBooks.length];
        for (int i = 0; i < schoolBooks.length; i++) {
            if(schoolBooks[i].getName().equals(name)) {
                newSchoolBooks[plus] = schoolBooks[i];
                plus++;
            }
        }
        return Arrays.copyOf(newSchoolBooks,plus);
    }

    @Override
    public boolean removeByName(String name) {
        int select = 0;
        int count = 0;
        if(findByName(name) == null) {
            return false;
        } else {
            for (SchoolBook counts : schoolBooks) {
                if (counts.getName().equals(name)) {
                    count++;
                }
            }
            SchoolBook[] newSchoolBooks = new SchoolBook[schoolBooks.length - count];
            for (SchoolBook sc : schoolBooks) {
                if (sc.getName() != name) {
                    newSchoolBooks[select] = sc;
                    select++;
                }
            }
            schoolBooks = Arrays.copyOf(newSchoolBooks,newSchoolBooks.length);

            return true;
        }
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
