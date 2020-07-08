#!/bin/bash

echo ""
echo "Applying migration WhatIsTheNameOfTheOtherPerson"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /whatIsTheNameOfTheOtherPerson                        controllers.WhatIsTheNameOfTheOtherPersonController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /whatIsTheNameOfTheOtherPerson                        controllers.WhatIsTheNameOfTheOtherPersonController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeWhatIsTheNameOfTheOtherPerson                  controllers.WhatIsTheNameOfTheOtherPersonController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeWhatIsTheNameOfTheOtherPerson                  controllers.WhatIsTheNameOfTheOtherPersonController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "whatIsTheNameOfTheOtherPerson.title = whatIsTheNameOfTheOtherPerson" >> ../conf/messages.en
echo "whatIsTheNameOfTheOtherPerson.heading = whatIsTheNameOfTheOtherPerson" >> ../conf/messages.en
echo "whatIsTheNameOfTheOtherPerson.checkYourAnswersLabel = whatIsTheNameOfTheOtherPerson" >> ../conf/messages.en
echo "whatIsTheNameOfTheOtherPerson.error.required = Enter whatIsTheNameOfTheOtherPerson" >> ../conf/messages.en
echo "whatIsTheNameOfTheOtherPerson.error.length = WhatIsTheNameOfTheOtherPerson must be 100 characters or less" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheNameOfTheOtherPersonUserAnswersEntry: Arbitrary[(WhatIsTheNameOfTheOtherPersonPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[WhatIsTheNameOfTheOtherPersonPage.type]";\
    print "        value <- arbitrary[String].suchThat(_.nonEmpty).map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryWhatIsTheNameOfTheOtherPersonPage: Arbitrary[WhatIsTheNameOfTheOtherPersonPage.type] =";\
    print "    Arbitrary(WhatIsTheNameOfTheOtherPersonPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(WhatIsTheNameOfTheOtherPersonPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def whatIsTheNameOfTheOtherPerson: Option[Row] = userAnswers.get(WhatIsTheNameOfTheOtherPersonPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"whatIsTheNameOfTheOtherPerson.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(lit\"$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.WhatIsTheNameOfTheOtherPersonController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"whatIsTheNameOfTheOtherPerson.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration WhatIsTheNameOfTheOtherPerson completed"
