describe('Customer Login and Navigation', () => {
  beforeEach(() => {
    // Visit the login page
    cy.visit('auth/login');
  });

  it('Should successfully log in with valid credentials and navigate to profile page', () => {
    // Enter real login credentials
    cy.get('input[formControlName="email"]').type('bb@gmail.com');
    cy.get('input[formControlName="password"]').type('Test123!');

    // Click the login button
    cy.get('button[type="submit"]').click();

    // Wait for the navigation to the homepage
    cy.url().should('include', '/');

    // Ensure the profile link is visible and click it
    cy.get('a.nav-link').contains('Profile').should('be.visible').click();

    // Wait for the profile page to load and check that the URL includes the profile page path
    cy.url().should('include', '/profile');

    // Check for some element that is unique to the profile page
    cy.get('h5.card-title').should('contain', 'Profile Information');
  });
});
