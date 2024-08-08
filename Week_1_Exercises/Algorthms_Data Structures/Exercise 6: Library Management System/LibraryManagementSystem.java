class Book {
    private String bookId;
    private String title;
    private String author;

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}


public class LibraryManagementSystem {
    private Book[] books;
    private int size;

    public LibraryManagementSystem(int capacity) {
        this.books = new Book[capacity];
        this.size = 0;
    }

    public void addBook(Book book) {
        if (size == books.length) {
            System.out.println("Library is full.");
            return;
        }
        books[size++] = book;
    }
    public Book binarySearchByTitle(String title) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = books[mid].getTitle().compareToIgnoreCase(title);
            if (comparison == 0) {
                return books[mid];
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public Book linearSearchByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }
    public static void display_book(Book lib) {
    	System.out.println("Book details:- Id: "+lib.getBookId()+", Title: "+lib.getTitle()+", Author: "+lib.getAuthor());
    }
    public static void main(String[] args) {
        LibraryManagementSystem lib = new LibraryManagementSystem(10);
        Book b1 = new Book("01", "Merchant of Venice", "William Shakespeare");
        Book b2 = new Book("02", "1984", "George Orwell");
        Book b3 = new Book("03", "The Great Gatsby", "F. Scott Fitzgerald");
        lib.addBook(b1);
        lib.addBook(b2);
        lib.addBook(b3);

        System.out.println("Library Books:");
        lib.display_book(b1);
        lib.display_book(b2);
        lib.display_book(b3);

        System.out.println("\nSearching for '1984' using Linear Search:");
        Book foundBook = lib.linearSearchByTitle("1984");
        if (foundBook != null) {
            System.out.println("Book Found!!");
            lib.display_book(foundBook);
        } else {
            System.out.println("Book not found.");
        }
        System.out.println("\nSearching for '1984' using Binary Search:");
        Book foundBook2 = lib.binarySearchByTitle("1984");
        if (foundBook2 != null) {
            System.out.println("Book Found!! ");
            lib.display_book(foundBook2);
        } else {
            System.out.println("Book not found.");
        }
    }
}
