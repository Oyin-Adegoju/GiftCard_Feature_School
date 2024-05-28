describe('Admin Login Page', () => {
  beforeEach(() => {
    // Bezoek de admin login pagina
    cy.visit('/admin-login');
  });

  it('Should successfully log in with valid credentials', () => {
    // Voer geldige inloggegevens in
    cy.get('input[name="email"]').type('admin@admin.com');
    cy.get('input[name="password"]').type('Admin@321');
    
    // Klik op de inlogknop
    cy.get('button[type="submit"]').click();
    
    // Controleer of we naar de admin gift cards pagina worden geleid
    cy.url().should('include', '/admin-gift-cards');
  });

  it('Should show an alert with invalid credentials', () => {
    // Voer ongeldige inloggegevens in
    cy.get('input[name="email"]').type('wrong@admin.com');
    cy.get('input[name="password"]').type('wrongpassword');
    
    // Gebruik een stub om het venster alert te onderscheppen
    cy.window().then((win) => {
      cy.stub(win, 'alert').as('alert');
    });

    // Klik op de inlogknop
    cy.get('button[type="submit"]').click();

    // Controleer of de alert wordt weergegeven met het juiste bericht
    cy.get('@alert').should('have.been.calledOnceWith', 'Invalid credentials');
  });
});
