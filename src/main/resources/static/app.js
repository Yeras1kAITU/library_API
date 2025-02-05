function displayResults(data, title) {
    const resultsDiv = document.getElementById('results');
    resultsDiv.innerHTML = `<h3>${title}</h3>`; // Set the title for the results

    if (Array.isArray(data)) {
        data.forEach(item => {
            const card = document.createElement('div');
            card.className = item.isbn ? 'book-card' : 'user-card';

            for (const key in item) {
                const p = document.createElement('p');

                // Handle borrowedItems separately
                if (key === 'borrowedItems' && Array.isArray(item[key])) {
                    p.textContent = `${key}:`;
                    const bookList = document.createElement('ul');
                    item[key].forEach(book => {
                        const bookItem = document.createElement('li');
                        bookItem.innerHTML = `<strong>Title:</strong> ${book.title}<br><strong>Author:</strong> ${book.author}<br><strong>ISBN:</strong> ${book.isbn}<br><strong>Genre:</strong> ${book.genre}`;
                        bookList.appendChild(bookItem);
                    });
                    p.appendChild(bookList);
                } else {
                    p.innerHTML = `<strong>${key}:</strong> ${item[key]}`;
                }

                card.appendChild(p);
            }

            const closeButton = document.createElement('button');
            closeButton.className = 'close-button';
            closeButton.textContent = 'Close';
            closeButton.onclick = () => card.remove();
            card.appendChild(closeButton);

            resultsDiv.appendChild(card);
        });
    } else {
        const card = document.createElement('div');
        card.className = 'book-card';

        for (const key in data) {
            const p = document.createElement('p');

            if (key === 'borrowedItems' && Array.isArray(data[key])) {
                p.textContent = `${key}:`;
                const bookList = document.createElement('ul');
                data[key].forEach(book => {
                    const bookItem = document.createElement('li');
                    bookItem.innerHTML = `<strong>Title:</strong> ${book.title}<br><strong>Author:</strong> ${book.author}<br><strong>ISBN:</strong> ${book.isbn}<br><strong>Genre:</strong> ${book.genre}`;
                    bookList.appendChild(bookItem);
                });
                p.appendChild(bookList);
            } else {
                p.innerHTML = `<strong>${key}:</strong> ${data[key]}`;
            }

            card.appendChild(p);
        }

        const closeButton = document.createElement('button');
        closeButton.className = 'close-button';
        closeButton.textContent = 'Close';
        closeButton.onclick = () => card.remove();
        card.appendChild(closeButton);

        resultsDiv.appendChild(card);
    }
}

function closeForm(formId) {
    document.getElementById(formId).style.display = 'none';
}

function openSearchForm() {
    document.getElementById('search-form').style.display = 'block';
}

function openFilterForm() {
    document.getElementById('filter-form').style.display = 'block';
}

function openAddBookForm() {
    document.getElementById('add-book-form').style.display = 'block';
}

function openAddUserForm() {
    document.getElementById('add-user-form').style.display = 'block';
}

function openBorrowBookForm() {
    document.getElementById('borrow-book-form').style.display = 'block';
}

function openBorrowedBooksForm() {
    document.getElementById('borrowed-books-form').style.display = 'block';
}

function openDeleteBookForm() {
    document.getElementById('delete-book-form').style.display = 'block';
}

function openDeleteUserForm() {
    document.getElementById('delete-user-form').style.display = 'block';
}

function getAllBooks() {
    fetch('http://localhost:8080/books')
        .then(response => response.json())
        .then(data => displayResults(data, 'All Books'))
        .catch(error => console.error('Error fetching books:', error));
}

function searchBooks() {
    const keyword = document.getElementById('search-keyword').value;
    fetch(`http://localhost:8080/books/search?keyword=${keyword}`)
        .then(response => response.json())
        .then(data => displayResults(data, 'Search Results'))
        .catch(error => console.error('Error searching books:', error));
}

function filterBooks() {
    const genre = document.getElementById('filter-genre').value;
    fetch(`http://localhost:8080/books/filter?genre=${genre}`)
        .then(response => response.json())
        .then(data => displayResults(data, 'Filtered Books'))
        .catch(error => console.error('Error filtering books:', error));
}

function getAllUsers() {
    fetch('http://localhost:8080/users')
        .then(response => response.json())
        .then(data => displayResults(data, 'All Users'))
        .catch(error => console.error('Error fetching users:', error));
}

function getBorrowedBooks() {
    const userId = document.getElementById('borrowed-books-user-id').value;
    fetch(`http://localhost:8080/users/${userId}/borrowed-books`)
        .then(response => response.json())
        .then(data => displayResults(data, 'Borrowed Books'))
        .catch(error => console.error('Error fetching borrowed books:', error));
}

function addBook() {
    const book = {
        isbn: document.getElementById('book-isbn').value,
        title: document.getElementById('book-title').value,
        author: document.getElementById('book-author').value,
        genre: document.getElementById('book-genre').value
    };

    fetch('http://localhost:8080/books', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(book)
    })
        .then(response => response.json())
        .then(data => {
            alert('Book added successfully!');
            closeForm('add-book-form');
        })
        .catch(error => console.error('Error adding book:', error));
}

function addUser() {
    const user = {
        userId: document.getElementById('user-id').value,
        name: document.getElementById('user-name').value
    };

    fetch('http://localhost:8080/users', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user)
    })
        .then(response => response.json())
        .then(data => {
            alert('User added successfully!');
            closeForm('add-user-form');
        })
        .catch(error => console.error('Error adding user:', error));
}

function assignBookToUser() {
    const userId = document.getElementById('borrow-user-id').value;
    const isbn = document.getElementById('borrow-isbn').value;

    fetch(`http://localhost:8080/users/${userId}/borrow?isbn=${isbn}`, {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                alert('Book assigned successfully!');
                closeForm('borrow-book-form');
            } else {
                throw new Error('Failed to assign book');
            }
        })
        .catch(error => console.error('Error assigning book:', error));
}

function deleteBook() {
    const isbn = document.getElementById('delete-book-isbn').value;

    fetch(`http://localhost:8080/books/${isbn}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                alert('Book deleted successfully!');
                closeForm('delete-book-form');
            } else {
                throw new Error('Failed to delete book');
            }
        })
        .catch(error => console.error('Error deleting book:', error));
}

function deleteUser() {
    const userId = document.getElementById('delete-user-id').value;

    fetch(`http://localhost:8080/users/${userId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                alert('User deleted successfully!');
                closeForm('delete-user-form');
            } else {
                throw new Error('Failed to delete user');
            }
        })
        .catch(error => console.error('Error deleting user:', error));
}