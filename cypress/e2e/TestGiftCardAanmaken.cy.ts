describe('Admin panel', () => {
  beforeEach(() => {
    // Bezoek de admin login pagina
    cy.visit('/admin-login');
  });

  it('Should succesfully view GiftCard details', () => {
    // Voer geldige inloggegevens in
    cy.get('input[name="email"]').type('admin@admin.com');
    cy.get('input[name="password"]').type('Admin@321');
    
    // Klik op de inlogknop
    cy.get('button[type="submit"]').click();
    
    // Controleer of we naar de admin gift cards pagina worden geleid
    cy.url().should('include', '/admin-gift-cards');
     // Controleer of de lijst met gift cards wordt weergegeven
    cy.get('.gift-card-container h2').contains('Gift Cards');
    cy.get('.gift-card-container table tbody tr').should('have.length.greaterThan', 0);
  });
  it('Should add a new gift card', () => {
    // Voer geldige inloggegevens in en log in
    cy.get('input[name="email"]').type('admin@admin.com');
    cy.get('input[name="password"]').type('Admin@321');
    cy.get('button[type="submit"]').click();
    cy.url().should('include', '/admin-gift-cards');

    // Vul het formulier in om een nieuwe gift card toe te voegen
    cy.get('input[formControlName="name"]').type('New Gift Card');
    cy.get('input[formControlName="amount"]').type('50');
    cy.get('input[formControlName="image"]').type('https://example.com/image.jpg');
    cy.get('button').contains('Add Gift Card').click();

    // Controleer of de nieuwe gift card is toegevoegd
    cy.get('.gift-card-container table tbody tr').should('contain', 'New Gift Card');
  });
 
});
