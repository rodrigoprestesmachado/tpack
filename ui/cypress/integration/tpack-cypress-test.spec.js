// tpack-cypress-test.spec.js created with Cypress
//
// Start writing your Cypress tests below!
// If you're unfamiliar with how Cypress works,
// check out the link below and learn how to write your first test:
// https://on.cypress.io/writing-first-test

describe('Testing the tpack form entire flow.', () => {
  it('intro-page', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.visit('http://localhost:8081/tpack/#/');
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
    /* ==== End Cypress Studio ==== */
  });

  it('page-1', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .mb-8 > .v-card__actions > .col > :nth-child(2) > .v-input__control > .v-input__slot > .v-label').click();
    cy.get(':nth-child(4) > .mb-8 > .v-card__actions > .col > :nth-child(3) > .v-input__control > .v-input__slot > .v-label').click();
    cy.get(':nth-child(5) > .mb-8 > .v-card__actions > .col > :nth-child(2) > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
    cy.get('#list-item-121-1').click();
    cy.get('.align-start').click({ force: true });
    cy.get(':nth-child(3) > .v-input__control > .v-input__slot > .v-select__slot > .v-input__append-inner > .v-input__icon > .v-icon').click({ force: true });
    cy.get('#list-item-133-2 > .v-list-item__content').click({ force: true });
    cy.get('.align-start').click({ force: true });
    cy.get(':nth-child(4) > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click({ force: true });
    cy.get('#list-item-143-2 > .v-list-item__content > .v-list-item__title').click({ force: true });
    cy.get('.align-start').click({ force: true });
    cy.get(':nth-child(6) > .v-label').click();
    cy.get(':nth-child(7) > .mb-8 > .v-card__actions > .col > .v-input > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
    cy.get('#list-item-153-2 > .v-list-item__content > .v-list-item__title').click();
    cy.get(':nth-child(3) > .v-btn').click();
  });
  it('page-2', function () {

    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content').click();
  });
  it('page-3', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container > [style="width: 2px; height: 2px; left: calc(100% - 1px); top: calc(50% - 1px);"]').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  /* ==== Generated with Cypress Studio ==== */
  it('page-4', function () {
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  it('page-5', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container > [style="width: 2px; height: 2px; left: calc(62.5% - 1px); top: calc(50% - 1px);"]').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__thumb-container > .v-slider__thumb').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container > [style="width: 2px; height: 2px; left: calc(22.5% - 1px); top: calc(50% - 1px);"]').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  it('page-6', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  it('page-7', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container > [style="width: 2px; height: 2px; left: calc(35% - 1px); top: calc(50% - 1px);"]').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  it('page-8', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container').click();
    cy.get(':nth-child(6) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content').click();
  });
  it('page-9', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get(':nth-child(3) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container > [style="width: 2px; height: 2px; left: calc(45% - 1px); top: calc(50% - 1px);"]').click();
    cy.get(':nth-child(4) > .v-input > .v-input__control > .v-input__slot > .v-slider').click();
    cy.get(':nth-child(5) > .v-input > .v-input__control > .v-input__slot > .v-slider > .v-slider__ticks-container > [style="width: 2px; height: 2px; left: calc(50% - 1px); top: calc(50% - 1px);"]').click();
    cy.get(':nth-child(3) > .v-label').click();
    cy.get(':nth-child(6) > .v-input__control > .v-input__slot > .v-label').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  it('page-10', function () {
    /* ==== Generated with Cypress Studio ==== */
    cy.get('.v-input--radio-group__input > :nth-child(2) > .v-label').click();
    cy.get(':nth-child(4) > .mb-8 > .v-card__actions > .col > .v-input > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
    cy.get('#list-item-378-2 > .v-list-item__content > .v-list-item__title').click();
    cy.get(':nth-child(5) > .mb-8 > .v-card__actions > .col > .v-input > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
    cy.get('#list-item-402-2 > .v-list-item__content > .v-list-item__title').click();
    cy.get(':nth-child(3) > .v-input__control > .v-input__slot > .v-select__slot > .v-select__selections').click();
    cy.get('#list-item-431-3 > .v-list-item__content > .v-list-item__title').click();
    cy.get(':nth-child(3) > .v-btn > .v-btn__content > .v-icon').click();
  });
  /* ==== Generated with Cypress Studio ==== */
});



