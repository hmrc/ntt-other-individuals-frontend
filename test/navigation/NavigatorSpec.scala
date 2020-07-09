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

package navigation

import base.SpecBase
import controllers.routes
import generators.Generators
import pages._
import models._
import org.scalacheck.Arbitrary.arbitrary
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks

class NavigatorSpec extends SpecBase with ScalaCheckPropertyChecks with Generators {

  val navigator = new Navigator

  "Navigator" - {

    "in Normal mode" - {

      "must go from a page that doesn't exist in the route map to Index" in {

        case object UnknownPage extends Page

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(UnknownPage, NormalMode, answers)
              .mustBe(routes.IndexController.onPageLoad())
        }
      }

      "must go from Index Page to Do Other have control over trust page" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(IndexPage, NormalMode, answers)
              .mustBe(routes.DoOtherPeopleHaveControlOverTheTrustController.onPageLoad(NormalMode))
        }
      }

      "must go from Do Other have control over trust page to what is others name page" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(DoOtherPeopleHaveControlOverTheTrustPage, NormalMode, answers)
              .mustBe(routes.WhatIsTheNameOfTheOtherPersonController.onPageLoad(NormalMode))
        }
      }

      "must go from What is others name page to Do you know their DoB" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(WhatIsTheNameOfTheOtherPersonPage, NormalMode, answers)
              .mustBe(routes.DoYouKnowTheirDateOfBirthController.onPageLoad(NormalMode))
        }
      }

      "must go from Do you know their DoB to What is their DoB" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(DoYouKnowTheirDateOfBirthPage, NormalMode, answers)
              .mustBe(routes.WhatIsTheirDateOfBirthController.onPageLoad(NormalMode))
        }
      }

      "must go from What is their DoB to Do you know their nationality?" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(WhatIsTheirDateOfBirthPage, NormalMode, answers)
              .mustBe(routes.DoYouKnowTheirNationalityController.onPageLoad(NormalMode))
        }
      }

      "must go from Do you know their nationality to What is their nationality" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(DoYouKnowTheirNationalityPage, NormalMode, answers)
              .mustBe(routes.WhatIsTheirCountryOfNationalityController.onPageLoad(NormalMode))
        }
      }

      "must go from What is their nationality to Do Yyou know their country of residency" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(WhatIsTheirCountryOfNationalityPage, NormalMode, answers)
              .mustBe(routes.DoYouKnowTheirCountryOfResidencyController.onPageLoad(NormalMode))
        }
      }

      "must go from Do You know their country of residency to What is their country of residency" in {

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(DoYouKnowTheirCountryOfResidencyPage, NormalMode, answers)
              .mustBe(routes.WhatIsTheirCountryOfResidencyController.onPageLoad(NormalMode))
        }
      }
    }

    "in Check mode" - {

      "must go from a page that doesn't exist in the edit route map  to Check Your Answers" in {

        case object UnknownPage extends Page

        forAll(arbitrary[UserAnswers]) {
          answers =>

            navigator.nextPage(UnknownPage, CheckMode, answers)
              .mustBe(routes.CheckYourAnswersController.onPageLoad())
        }
      }
    }
  }
}
