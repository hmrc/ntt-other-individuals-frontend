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

package models

import play.api.data.Form
import play.api.i18n.Messages
import play.api.libs.json._
import uk.gov.hmrc.viewmodels._

sealed trait DoYouWantToAddAnotherIndividual

object DoYouWantToAddAnotherIndividual extends Enumerable.Implicits {

  case object YesIWantToAddThemNow extends WithName("yesIWantToAddThemNow") with DoYouWantToAddAnotherIndividual
  case object YesIWantToAddThemLater extends WithName("yesIWantToAddThemLater") with DoYouWantToAddAnotherIndividual
  case object NoIHaveAddedAllOtherIndividuals extends WithName("noIHaveAddedAllOtherIndividuals") with DoYouWantToAddAnotherIndividual

  val values: Seq[DoYouWantToAddAnotherIndividual] = Seq(
    YesIWantToAddThemNow,
    YesIWantToAddThemLater,
    NoIHaveAddedAllOtherIndividuals
  )

  def radios(form: Form[_])(implicit messages: Messages): Seq[Radios.Item] = {

    val field = form("value")
    val items = Seq(
      Radios.Radio(msg"doYouWantToAddAnotherIndividual.yesIWantToAddThemNow", YesIWantToAddThemNow.toString),
      Radios.Radio(msg"doYouWantToAddAnotherIndividual.yesIWantToAddThemLater", YesIWantToAddThemLater.toString),
      Radios.Radio(msg"doYouWantToAddAnotherIndividual.yesIWantToAddThemLater", NoIHaveAddedAllOtherIndividuals.toString)
    )

    Radios(field, items)
  }

  implicit val enumerable: Enumerable[DoYouWantToAddAnotherIndividual] =
    Enumerable(values.map(v => v.toString -> v): _*)
}
