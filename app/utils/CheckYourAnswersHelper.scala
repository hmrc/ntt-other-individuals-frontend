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

package utils

import java.time.format.DateTimeFormatter

import controllers.routes
import models.{CheckMode, UserAnswers}
import pages._
import play.api.i18n.Messages
import CheckYourAnswersHelper._
import uk.gov.hmrc.viewmodels._
import uk.gov.hmrc.viewmodels.SummaryList._
import uk.gov.hmrc.viewmodels.Text.Literal

class CheckYourAnswersHelper(userAnswers: UserAnswers)(implicit messages: Messages) {

  def doYouKnowTheirDateOfBirth: Option[Row] = userAnswers.get(DoYouKnowTheirDateOfBirthPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowTheirDateOfBirth.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.DoYouKnowTheirDateOfBirthController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowTheirDateOfBirth.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheNameOfTheOtherPerson: Option[Row] = userAnswers.get(WhatIsTheNameOfTheOtherPersonPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheNameOfTheOtherPerson.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(lit"$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheNameOfTheOtherPersonController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheNameOfTheOtherPerson.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doOtherPeopleHaveControlOverTheTrust: Option[Row] = userAnswers.get(DoOtherPeopleHaveControlOverTheTrustPage) map {
    answer =>
      Row(
        key     = Key(msg"doOtherPeopleHaveControlOverTheTrust.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.DoOtherPeopleHaveControlOverTheTrustController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doOtherPeopleHaveControlOverTheTrust.checkYourAnswersLabel"))
          )
        )
      )
  }

  private def yesOrNo(answer: Boolean): Content =
    if (answer) {
      msg"site.yes"
    } else {
      msg"site.no"
    }
}

object CheckYourAnswersHelper {

  private val dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy")
}
