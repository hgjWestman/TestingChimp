Feature: Register Chimp

  Scenario Outline: I want to try to register an account at mailchimp.com, *u* is an unique value, and - is an empty one

  Given I open "<browser>" and navigates to the website
  And I input "<email>" as my email address
  And then choose "<username>" as my username
  And input "<password>" as my password.
  When I am pressing Sign up I cross my fingers
  Then hope its a success or at least show the correct "<error>".

  Examples:
    |email              |username                                                                                             |password   |browser    |error                                                                            |
    |user.*u*0@hgj.se   |hgjuser*u*                                                                                           |MamboNo5!  |edge       |none                                                                             |
    |user.*u*0@hgj.se   |myUserNameIsToLongToBeRememberedByAnyoneAfterTheShipHasSailedToALandFarFarAwayWhereTheSunNeverSets*u*|MamboNo5!  |edge       |Enter a value less than 100 characters long                                      |
    |goran@stokasen.se  |goran@stokasen.se                                                                                    |MamboNo5!  |edge       |Great minds think alike - someone already has this username. If it's you, log in.|
    |-                  |hgjuser*u*                                                                                           |MamboNo5!  |edge       |An email address must contain a single @.                                        |
    |user.*u*0@hgj.se   |hgjuser*u*                                                                                           |MamboNo5!  |chrome     |none                                                                             |
    |user.*u*0@hgj.se   |myUserNameIsToLongToBeRememberedByAnyoneAfterTheShipHasSailedToALandFarFarAwayWhereTheSunNeverSets*u*|MamboNo5!  |chrome     |Enter a value less than 100 characters long                                      |
    |goran@stokasen.se  |goran@stokasen.se                                                                                    |MamboNo5!  |chrome     |Great minds think alike - someone already has this username. If it's you, log in.|
    |-                  |hgjuser*u*                                                                                           |MamboNo5!  |chrome     |An email address must contain a single @.                                        |