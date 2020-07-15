#!/bin/bash

echo ""
echo "Applying migration AreTheyLegallyIncapble"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /areTheyLegallyIncapble                        controllers.AreTheyLegallyIncapbleController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /areTheyLegallyIncapble                        controllers.AreTheyLegallyIncapbleController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeAreTheyLegallyIncapble                  controllers.AreTheyLegallyIncapbleController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeAreTheyLegallyIncapble                  controllers.AreTheyLegallyIncapbleController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "areTheyLegallyIncapble.title = areTheyLegallyIncapble" >> ../conf/messages.en
echo "areTheyLegallyIncapble.heading = areTheyLegallyIncapble" >> ../conf/messages.en
echo "areTheyLegallyIncapble.checkYourAnswersLabel = areTheyLegallyIncapble" >> ../conf/messages.en
echo "areTheyLegallyIncapble.error.required = Select yes if areTheyLegallyIncapble" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryAreTheyLegallyIncapbleUserAnswersEntry: Arbitrary[(AreTheyLegallyIncapblePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[AreTheyLegallyIncapblePage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryAreTheyLegallyIncapblePage: Arbitrary[AreTheyLegallyIncapblePage.type] =";\
    print "    Arbitrary(AreTheyLegallyIncapblePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(AreTheyLegallyIncapblePage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def areTheyLegallyIncapble: Option[Row] = userAnswers.get(AreTheyLegallyIncapblePage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"areTheyLegallyIncapble.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.AreTheyLegallyIncapbleController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"areTheyLegallyIncapble.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration AreTheyLegallyIncapble completed"
