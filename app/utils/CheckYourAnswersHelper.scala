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
import services.CountryService
import uk.gov.hmrc.viewmodels._
import uk.gov.hmrc.viewmodels.SummaryList._
import uk.gov.hmrc.viewmodels.Text.Literal

class CheckYourAnswersHelper(userAnswers: UserAnswers, countryService: CountryService)(implicit messages: Messages) {

  def doYouWantToAddAnotherIndividual: Option[Row] = userAnswers.get(DoYouWantToAddAnotherIndividualPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouWantToAddAnotherIndividual.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(msg"doYouWantToAddAnotherIndividual.$answer"),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.DoYouWantToAddAnotherIndividualController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouWantToAddAnotherIndividual.checkYourAnswersLabel"))
          )
        )
      )
  }

  def areTheyLegallyIncapable: Option[Row] = userAnswers.get(AreTheyLegallyIncapablePage) map {
    answer =>
      Row(
        key     = Key(msg"areTheyLegallyIncapable.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.AreTheyLegallyIncapableController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"areTheyLegallyIncapable.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirCountryOfResidency: Option[Row] = userAnswers.get(WhatIsTheirCountryOfResidencyPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirCountryOfResidency.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirCountryOfResidencyController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirCountryOfResidency.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowTheirCountryOfResidency: Option[Row] = userAnswers.get(DoYouKnowTheirCountryOfResidencyPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowTheirCountryOfResidency.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.DoYouKnowTheirCountryOfResidencyController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowTheirCountryOfResidency.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirCountryOfNationality: Option[Row] = userAnswers.get(WhatIsTheirCountryOfNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirCountryOfNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(country(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirCountryOfNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirCountryOfNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  def doYouKnowTheirNationality: Option[Row] = userAnswers.get(DoYouKnowTheirNationalityPage) map {
    answer =>
      Row(
        key     = Key(msg"doYouKnowTheirNationality.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(yesOrNo(answer)),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.DoYouKnowTheirNationalityController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"doYouKnowTheirNationality.checkYourAnswersLabel"))
          )
        )
      )
  }

  def whatIsTheirDateOfBirth: Option[Row] = userAnswers.get(WhatIsTheirDateOfBirthPage) map {
    answer =>
      Row(
        key     = Key(msg"whatIsTheirDateOfBirth.checkYourAnswersLabel", classes = Seq("govuk-!-width-one-half")),
        value   = Value(Literal(answer.format(dateFormatter))),
        actions = List(
          Action(
            content            = msg"site.edit",
            href               = routes.WhatIsTheirDateOfBirthController.onPageLoad(CheckMode).url,
            visuallyHiddenText = Some(msg"site.edit.hidden".withArgs(msg"whatIsTheirDateOfBirth.checkYourAnswersLabel"))
          )
        )
      )
  }

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

  private def country(code: String): Content =
    lit"${countryService.getCountryByCode(code).getOrElse("")}"

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
