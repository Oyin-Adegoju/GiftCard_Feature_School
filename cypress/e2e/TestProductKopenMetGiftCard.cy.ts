describe('Customer Login and Navigation', () => {
  beforeEach(() => {
    // Bezoek de klant login pagina
    cy.visit('auth/login');
  });

  it('Should successfully log in with valid credentials', () => {
    // Intercept de API-aanroep voor succesvolle login
    cy.intercept('POST', '**/auth/login', {
      statusCode: 200,
      body: {
        id: 1,
        token: 'valid-token'
      }
    }).as('loginRequest');

    // Voer geldige inloggegevens in
    cy.get('input[formControlName="email"]').type('customer@example.com');
    cy.get('input[formControlName="password"]').type('ValidPassword123');
    
    // Klik op de inlogknop
    cy.get('button[type="submit"]').click();
    
    // Wacht op de API-aanroep en controleer de navigatie
    cy.wait('@loginRequest');
    cy.url().should('include', '/');
  });

  it('Should navigate to the Products page and display the list of products', () => {
    // Eerst inloggen zoals in de vorige test
    cy.intercept('POST', '**/auth/login', {
      statusCode: 200,
      body: {
        id: 1,
        token: 'valid-token'
      }
    }).as('loginRequest');

    // Voer geldige inloggegevens in
    cy.get('input[formControlName="email"]').type('customer@example.com');
    cy.get('input[formControlName="password"]').type('ValidPassword123');
    
    // Klik op de inlogknop
    cy.get('button[type="submit"]').click();
    
    // Wacht op de API-aanroep en controleer de navigatie
    cy.wait('@loginRequest');
    cy.url().should('include', '/');

    // Intercept de API-aanroep voor het ophalen van producten
    cy.intercept('GET', '**/products', {
      statusCode: 200,
      body: [
        {
          id: 1,
          name: 'Product 1',
          description: 'Description of Product 1',
          price: 29.99,
          imgURL: 'https://example.com/product1.jpg',
          amount: 10,
          specifications: 'Specifications of Product 1',
          publisher: 'Publisher 1',
          releaseDate: '2022-01-01'
        },
        {
          id: 2,
          name: 'Product 2',
          description: 'Description of Product 2',
          price: 49.99,
          imgURL: 'https://example.com/product2.jpg',
          amount: 5,
          specifications: 'Specifications of Product 2',
          publisher: 'Publisher 2',
          releaseDate: '2022-02-01'
        }
      ]
    }).as('getProducts');

    // Klik op het "Products" tabblad in de navigatiebalk
    cy.get('.navbar-nav').contains('Products').click();

    // Wacht op de API-aanroep en controleer of de lijst met producten wordt weergegeven
    cy.wait('@getProducts');

    // Controleer of we op de "Products" pagina zijn door de titel te controleren
    cy.get('h3').should('contain', 'All Games');

    // Controleer of de producten worden weergegeven
    cy.get('app-product-thumbnail').should('have.length', 2);
    cy.get('app-product-thumbnail').first().should('contain', 'Product 1');
    cy.get('app-product-thumbnail').last().should('contain', 'Product 2');
  });
});
