# Library Management System (Java)

A beginner-friendly console application designed for practicing Git and GitHub workflows. Runs in Eclipse and uses only the Java standard library.

## Features
- Add and view books
- Search for a book by ID
- Issue a book to a borrower
- Return an issued book
- Show total, available, and issued book counts
- Save and load records using a local text file
- Basic input validation

## Concepts
Java classes and objects, encapsulation, `ArrayList`, loops, methods, conditionals, exception handling, and file handling.

## Requirements
- JDK 8 or later
- Eclipse IDE for Java Developers

## Run in Eclipse
1. Extract this ZIP.
2. In Eclipse, choose **File → New → Java Project** and name it `LibraryManagementSystem`.
3. If Eclipse creates `module-info.java`, you may delete it for this beginner project.
4. Copy `Main.java`, `Book.java`, and `Library.java` from the extracted `src` folder into the Eclipse project's `src` folder.
5. Keep the classes in the default package (no `package` declarations).
6. Right-click `Main.java` and select **Run As → Java Application**.

Runtime records are stored in `data/books.txt` relative to the project's working directory.

## GitHub initial upload
Create an empty GitHub repository named `LibraryManagementSystem`, then run these commands from the project root:

```bash
git init
git add .
git commit -m "Initial commit: Library Management System"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/LibraryManagementSystem.git
git push -u origin main
```

Replace `YOUR-USERNAME` with your GitHub username.

## Practice a feature branch
```bash
git switch -c feature/book-search
# Make and test your changes
git add .
git commit -m "Improve book search"
git push -u origin feature/book-search
git switch main
git merge feature/book-search
git push origin main
```

If your instructor requires a Pull Request, push the branch and merge it through GitHub instead.

## Suggested test
Add book ID `101`, title `Clean Code`, author `Robert C. Martin`, category `Software`. View and search for it, issue it to a borrower, check statistics, return it, then restart the app to verify persistence.

This is a learning project, not a production library system. The runtime data file is ignored by Git to avoid accidentally committing personal test records.
