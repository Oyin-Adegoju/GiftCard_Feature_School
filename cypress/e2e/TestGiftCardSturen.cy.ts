//\/ <reference types="cypress" />

describe('GiftCardPopupComponent', () => {
  beforeEach(() => {
    // Bezoek de pagina met de "send now" knop
    cy.visit('http://localhost:4200/gift-cards/5');
  });

  it('should handle unauthorized error', () => {
    // Klik op de "send now" knop om de popup te openen
    cy.contains('button', 'Send Now').click();

    // Typ de e-mail in het formulier binnen de pop-up
    cy.get('input[type="email"]'   ).type('bb@gmail.com');

    // Intercept het netwerkverzoek met een Unauthorized fout
    cy.intercept('POST', '/api/gift-cards/bbb@gmail.com/5', {
      statusCode: 401,
      body: { error: { message: 'Unauthorized' } }
    }).as('sendGiftCardUnauthorized');

    // Klik op de submit knop
    cy.get('button[type="submit"]').click();

    // Controleer of de popup gesloten is
    cy.get('.popup').should('not.exist');

    
  });
});
