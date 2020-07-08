#!/bin/bash

echo ""
echo "Applying migration DoOtherPeopleHaveControlOverTheTrust"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doOtherPeopleHaveControlOverTheTrust                        controllers.DoOtherPeopleHaveControlOverTheTrustController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doOtherPeopleHaveControlOverTheTrust                        controllers.DoOtherPeopleHaveControlOverTheTrustController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeDoOtherPeopleHaveControlOverTheTrust                  controllers.DoOtherPeopleHaveControlOverTheTrustController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeDoOtherPeopleHaveControlOverTheTrust                  controllers.DoOtherPeopleHaveControlOverTheTrustController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doOtherPeopleHaveControlOverTheTrust.title = doOtherPeopleHaveControlOverTheTrust" >> ../conf/messages.en
echo "doOtherPeopleHaveControlOverTheTrust.heading = doOtherPeopleHaveControlOverTheTrust" >> ../conf/messages.en
echo "doOtherPeopleHaveControlOverTheTrust.checkYourAnswersLabel = doOtherPeopleHaveControlOverTheTrust" >> ../conf/messages.en
echo "doOtherPeopleHaveControlOverTheTrust.error.required = Select yes if doOtherPeopleHaveControlOverTheTrust" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoOtherPeopleHaveControlOverTheTrustUserAnswersEntry: Arbitrary[(DoOtherPeopleHaveControlOverTheTrustPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[DoOtherPeopleHaveControlOverTheTrustPage.type]";\
    print "        value <- arbitrary[Boolean].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoOtherPeopleHaveControlOverTheTrustPage: Arbitrary[DoOtherPeopleHaveControlOverTheTrustPage.type] =";\
    print "    Arbitrary(DoOtherPeopleHaveControlOverTheTrustPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(DoOtherPeopleHaveControlOverTheTrustPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def doOtherPeopleHaveControlOverTheTrust: Option[Row] = userAnswers.get(DoOtherPeopleHaveControlOverTheTrustPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"doOtherPeopleHaveControlOverTheTrust.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(yesOrNo(answer)),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.DoOtherPeopleHaveControlOverTheTrustController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"doOtherPeopleHaveControlOverTheTrust.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration DoOtherPeopleHaveControlOverTheTrust completed"
