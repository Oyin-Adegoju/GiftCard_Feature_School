describe('Gift Cards Page', () => {
  it('Should navigate to the Gift Cards page when the tab is clicked', () => {
    // Bezoek de homepagina
    cy.visit('/');

    // Klik op het "Gift Cards" tabblad in de navigatiebalk
    cy.get('.navbar-nav').contains('Gift Cards').click();

    // Controleer of we op de "Gift Cards" pagina zijn door de titel te controleren
    cy.get('h3').should('contain', 'All Gift Cards');
  });

  it('Should show gift card details when the details button is clicked', () => {
    // Bezoek de homepagina
    cy.visit('/');

    // Klik op het "Gift Cards" tabblad in de navigatiebalk
    cy.get('.navbar-nav').contains('Gift Cards').click();

    // Controleer of we op de "Gift Cards" pagina zijn door de titel te controleren
    cy.get('h3').should('contain', 'All Gift Cards');

    // Klik op de "Details" knop van de eerste gift card
    cy.get('app-gift-card-thumnail').first().find('a.btn-primary').contains('Details').click();

    // Controleer of de navigatie naar de details pagina succesvol was
    cy.url().should('include', '/gift-cards/');

    // Controleer of de details van de gift card worden weergegeven
    cy.get('p').should('exist');
  });

  it('Should show "Loading gift cards..." message if gift cards cannot be loaded', () => {
    // Bezoek de homepagina
    cy.visit('/');

    // Simuleer het scenario waarin gift cards niet kunnen worden geladen door de API call te onderscheppen
    cy.intercept('GET', '**/giftCards', {
      statusCode: 500,
      body: {},
    });

    // Klik op het "Gift Cards" tabblad in de navigatiebalk
    cy.get('.navbar-nav').contains('Gift Cards').click();

    // Controleer of de "Loading gift cards..." melding wordt weergegeven
    cy.get('p').should('contain', 'Loading giftcards...');
  });
});
