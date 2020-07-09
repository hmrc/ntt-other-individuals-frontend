#!/bin/bash

echo ""
echo "Applying migration DoYouWantToAddAnotherIndividual"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /doYouWantToAddAnotherIndividual                        controllers.DoYouWantToAddAnotherIndividualController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /doYouWantToAddAnotherIndividual                        controllers.DoYouWantToAddAnotherIndividualController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeDoYouWantToAddAnotherIndividual                  controllers.DoYouWantToAddAnotherIndividualController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeDoYouWantToAddAnotherIndividual                  controllers.DoYouWantToAddAnotherIndividualController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "doYouWantToAddAnotherIndividual.title = doYouWantToAddAnotherIndividual" >> ../conf/messages.en
echo "doYouWantToAddAnotherIndividual.heading = doYouWantToAddAnotherIndividual" >> ../conf/messages.en
echo "doYouWantToAddAnotherIndividual.yesIWantToAddThemNow = YesIWantToAdThemNow" >> ../conf/messages.en
echo "doYouWantToAddAnotherIndividual.yesIWantToAddThemLater = YesIWantToAddThemLater" >> ../conf/messages.en
echo "doYouWantToAddAnotherIndividual.checkYourAnswersLabel = doYouWantToAddAnotherIndividual" >> ../conf/messages.en
echo "doYouWantToAddAnotherIndividual.error.required = Select doYouWantToAddAnotherIndividual" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouWantToAddAnotherIndividualUserAnswersEntry: Arbitrary[(DoYouWantToAddAnotherIndividualPage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[DoYouWantToAddAnotherIndividualPage.type]";\
    print "        value <- arbitrary[DoYouWantToAddAnotherIndividual].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouWantToAddAnotherIndividualPage: Arbitrary[DoYouWantToAddAnotherIndividualPage.type] =";\
    print "    Arbitrary(DoYouWantToAddAnotherIndividualPage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to ModelGenerators"
awk '/trait ModelGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryDoYouWantToAddAnotherIndividual: Arbitrary[DoYouWantToAddAnotherIndividual] =";\
    print "    Arbitrary {";\
    print "      Gen.oneOf(DoYouWantToAddAnotherIndividual.values.toSeq)";\
    print "    }";\
    next }1' ../test/generators/ModelGenerators.scala > tmp && mv tmp ../test/generators/ModelGenerators.scala

echo "Adding to UserAnswersGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(DoYouWantToAddAnotherIndividualPage.type, JsValue)] ::";\
    next }1' ../test/generators/UserAnswersGenerator.scala > tmp && mv tmp ../test/generators/UserAnswersGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class CheckYourAnswersHelper/ {\
     print;\
     print "";\
     print "  def doYouWantToAddAnotherIndividual: Option[Row] = userAnswers.get(DoYouWantToAddAnotherIndividualPage) map {";\
     print "    answer =>";\
     print "      Row(";\
     print "        key     = Key(msg\"doYouWantToAddAnotherIndividual.checkYourAnswersLabel\", classes = Seq(\"govuk-!-width-one-half\")),";\
     print "        value   = Value(msg\"doYouWantToAddAnotherIndividual.$answer\"),";\
     print "        actions = List(";\
     print "          Action(";\
     print "            content            = msg\"site.edit\",";\
     print "            href               = routes.DoYouWantToAddAnotherIndividualController.onPageLoad(CheckMode).url,";\
     print "            visuallyHiddenText = Some(msg\"site.edit.hidden\".withArgs(msg\"doYouWantToAddAnotherIndividual.checkYourAnswersLabel\"))";\
     print "          )";\
     print "        )";\
     print "      )";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration DoYouWantToAddAnotherIndividual completed"
