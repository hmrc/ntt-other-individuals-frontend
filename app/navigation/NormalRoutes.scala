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

import controllers.routes
import models.{NormalMode, UserAnswers}
import pages._
import play.api.mvc.Call

object NormalRoutes {
  val routeMap: Page => UserAnswers => Call = {
    case IndexPage                                  => _ => routes.DoOtherPeopleHaveControlOverTheTrustController.onPageLoad(NormalMode)
    case DoOtherPeopleHaveControlOverTheTrustPage   => _ => routes.WhatIsTheNameOfTheOtherPersonController.onPageLoad(NormalMode)
    case WhatIsTheNameOfTheOtherPersonPage          => _ => routes.DoYouKnowTheirDateOfBirthController.onPageLoad(NormalMode)
    case DoYouKnowTheirDateOfBirthPage              => _ => routes.WhatIsTheirDateOfBirthController.onPageLoad(NormalMode)
    case WhatIsTheirDateOfBirthPage                 => _ => routes.DoYouKnowTheirNationalityController.onPageLoad(NormalMode)
    case DoYouKnowTheirNationalityPage              => _ => routes.WhatIsTheirCountryOfNationalityController.onPageLoad(NormalMode)
    case WhatIsTheirCountryOfNationalityPage        => _ => routes.DoYouKnowTheirCountryOfResidencyController.onPageLoad(NormalMode)
    case DoYouKnowTheirCountryOfResidencyPage       => _ => routes.WhatIsTheirCountryOfResidencyController.onPageLoad(NormalMode)
    case WhatIsTheirCountryOfResidencyPage          => _ => routes.AreTheyLegallyIncapableController.onPageLoad(NormalMode)
    case AreTheyLegallyIncapablePage                => _ => routes.CheckYourAnswersController.onPageLoad()
    case CheckYourAnswersPage                       => _ => routes.DoYouWantToAddAnotherIndividualController.onPageLoad(NormalMode)
    case _                                          => _ => routes.IndexController.onPageLoad()
  }
}
