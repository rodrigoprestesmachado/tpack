// dfd_teste_Cypress.spec.js created with Cypress
//
// Start writing your Cypress tests below!
// If you're unfamiliar with how Cypress works,
// check out the link below and learn how to write your first test:
// https://on.cypress.io/writing-first-test
/* ==== Test Created with Cypress Studio ==== */
it('Teste_TPACK_Cypress', function() {
  /* ==== Generated with Cypress Studio ==== */
  cy.visit('http://localhost:8081/tpack/#/');
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click({ force: true });
  cy.get(':nth-child(3) > .mb-8 > .v-card__actions > .col > :nth-child(4) > .v-input__control > .v-input__slot > .v-input--selection-controls__input > .v-input--selection-controls__ripple').click();
  cy.get(':nth-child(6) > .v-input__control > .v-input__slot > .v-input--selection-controls__input > .v-input--selection-controls__ripple').click();
  /* ==== End Cypress Studio ==== */
  /* ==== Generated with Cypress Studio ==== */
  cy.get(':nth-child(5) > .mb-8 > .v-card__actions > .col > :nth-child(2) > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
  cy.get('#list-item-121-3 > .v-list-item__action > .v-simple-checkbox > .v-icon').click();
  cy.get('.align-start').click({ force: true });
  cy.get(':nth-child(3) > .v-input__control > .v-input__slot > .v-select__slot > .v-input__append-inner').click();
  cy.get('#list-item-133-0 > .v-list-item__action > .v-simple-checkbox > .v-icon').click();
  cy.get('.align-start').click({ force: true });
  cy.get(':nth-child(4) > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
  cy.get('#list-item-143-0').click();
  cy.get('.align-start').click({ force: true });
  cy.get('.v-input--radio-group__input > :nth-child(7)').click();
  cy.get('.align-start').click({ force: true });
  cy.get(':nth-child(7) > .mb-8 > .v-card__actions > .col > .v-input > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
  cy.get('#list-item-153-3 > .v-list-item__content > .v-list-item__title').click();
  cy.get('.align-start').click({ force: true });
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label > div > span').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label > div').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get('.col-md-5 > :nth-child(3)').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container').click({ force: true });
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label > div > span').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label > div > span').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label').click();
  cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb-label-container > .v-slider__thumb-label > div > span').click();
  cy.get('.v-input--radio-group__input > :nth-child(1) > .v-label').click();
  cy.get(':nth-child(6) > .v-input__control > .v-input__slot > .v-label').click();
  cy.get(':nth-child(5) > .v-input__control > .v-input__slot > .v-label').click();
  cy.get(':nth-child(2) > .v-input__control > .v-input__slot > .v-label').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  cy.get(':nth-child(1) > .v-label').click();
  cy.get(':nth-child(4) > .mb-8 > .v-card__actions > .col > .v-input > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
  cy.get('#list-item-378-18 > .v-list-item__content > .v-list-item__title').click();
  cy.get('.align-start').click();
  cy.get(':nth-child(5) > .mb-8 > .v-card__actions > .col > .v-input > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
  cy.get('#list-item-422-19 > .v-list-item__content').click();
  cy.get(':nth-child(3) > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
  cy.get('#list-item-458-10 > .v-list-item__content').click();
  cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  //cy.get('#input-481').click({ force: true });
  cy.get('.ma-10 > .v-btn__content').click();
  /* ==== End Cypress Studio ==== */
});
