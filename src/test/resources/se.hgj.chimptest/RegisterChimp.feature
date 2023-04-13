Feature: Register Chimp

  Scenario Outline: I want to try to register an account at mailchimp.com, *u* is an unique value, and - is an empty one

  Given I open "<browser>" and navigates to the website
  And I input "<email>" as my email address
  And then choose "<username>" as my username
  And input "<password>" as my password.
  When I am pressing Sign up I cross my fingers
  Then hope its a success.

  Examples:
    |email              |username                                                                                             |password   |browser    |
    |user.*u*0@hgj.se   |hgjuser*u*                                                                                           |MamboNo5!  |edge       |
    |user.*u*0@hgj.se   |myUserNameIsToLongToBeRememberedByAnyoneAfterTheShipHasSailedToALandFarFarAwayWhereTheSunNeverSets*u*|MamboNo5!  |edge       |
    |goran@stokasen.se  |goran@stokasen.se                                                                                    |MamboNo5!  |edge       |
    |-                  |hgjuser*u*                                                                                           |MamboNo5!  |edge       |
    |user.*u*0@hgj.se   |hgjuser*u*                                                                                           |MamboNo5!  |chrome     |
    |user.*u*0@hgj.se   |myUserNameIsToLongToBeRememberedByAnyoneAfterTheShipHasSailedToALandFarFarAwayWhereTheSunNeverSets*u*|MamboNo5!  |chrome     |
    |goran@stokasen.se  |goran@stokasen.se                                                                                    |MamboNo5!  |chrome     |
    |-                  |hgjuser*u*                                                                                           |MamboNo5!  |chrome     |