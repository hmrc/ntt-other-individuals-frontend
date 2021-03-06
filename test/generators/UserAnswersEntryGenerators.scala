/*
 * Copyright 2020 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package generators

import models._
import org.scalacheck.Arbitrary
import org.scalacheck.Arbitrary.arbitrary
import pages._
import play.api.libs.json.{JsValue, Json}

trait UserAnswersEntryGenerators extends PageGenerators with ModelGenerators {

  implicit lazy val arbitraryDoYouWantToAddAnotherIndividualUserAnswersEntry: Arbitrary[(DoYouWantToAddAnotherIndividualPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouWantToAddAnotherIndividualPage.type]
        value <- arbitrary[DoYouWantToAddAnotherIndividual].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryAreTheyLegallyIncapbleUserAnswersEntry: Arbitrary[(AreTheyLegallyIncapablePage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[AreTheyLegallyIncapablePage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirCountryOfResidencyUserAnswersEntry: Arbitrary[(WhatIsTheirCountryOfResidencyPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirCountryOfResidencyPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowTheirCountryOfResidencyUserAnswersEntry: Arbitrary[(DoYouKnowTheirCountryOfResidencyPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowTheirCountryOfResidencyPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirCountryOfNationalityUserAnswersEntry: Arbitrary[(WhatIsTheirCountryOfNationalityPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirCountryOfNationalityPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowTheirNationalityUserAnswersEntry: Arbitrary[(DoYouKnowTheirNationalityPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowTheirNationalityPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheirDateOfBirthUserAnswersEntry: Arbitrary[(WhatIsTheirDateOfBirthPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheirDateOfBirthPage.type]
        value <- arbitrary[Int].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoYouKnowTheirDateOfBirthUserAnswersEntry: Arbitrary[(DoYouKnowTheirDateOfBirthPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoYouKnowTheirDateOfBirthPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryWhatIsTheNameOfTheOtherPersonUserAnswersEntry: Arbitrary[(WhatIsTheNameOfTheOtherPersonPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[WhatIsTheNameOfTheOtherPersonPage.type]
        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))
      } yield (page, value)
    }

  implicit lazy val arbitraryDoOtherPeopleHaveControlOverTheTrustUserAnswersEntry: Arbitrary[(DoOtherPeopleHaveControlOverTheTrustPage.type, JsValue)] =
    Arbitrary {
      for {
        page  <- arbitrary[DoOtherPeopleHaveControlOverTheTrustPage.type]
        value <- arbitrary[Boolean].map(Json.toJson(_))
      } yield (page, value)
    }
}
