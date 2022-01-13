package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if(findByFullName(author.getName(),author.getLastName()) !=null) {
            return false;
        } else {
            Author[] authorsNew = Arrays.copyOf(authors,authors.length+1);
            authorsNew[authorsNew.length-1] = author;
             authors = Arrays.copyOf(authorsNew, authorsNew.length);
        }
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for(Author authorFullName : authors) {
            if(authorFullName.getName().equals(name) && authorFullName.getLastName().equals(lastname)) {
                return authorFullName;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        int count = 0;
      if(findByFullName(author.getName(), author.getLastName())==null) {
          return false;
      } else {
          Author[] authorsNew = new Author[authors.length - 1];
          for (int i = 0; i < authors.length; i++) {
              if(authors[i] != author) {
                  authorsNew[count] = authors[i];
                  count++;
              }
      }
          authors = Arrays.copyOf(authorsNew, authorsNew.length);
          System.out.println(Arrays.toString(authors));
      }

      return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
