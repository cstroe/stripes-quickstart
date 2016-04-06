package com.github.cstroe.sqs.www

import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.HtmlUnit

class NotebookPageSpec extends FunSpec with BeforeAndAfter with ShouldMatchers with HtmlUnit with Eventually {

  before {
    go to StripesUtil.getUrl(classOf[DbUnitActionBean])
  }

  describe("The add notebook page") {
    it("should create a notebook") {
      go to StripesUtil.getUrl(classOf[NotebookActionBean])
      click on "notebookName"
      textField("notebookName").value = "My New Notebook"
      submit

      eventually {
        pageTitle shouldBe "View Notes"
      }

      val notebookNames = findAll(cssSelector("tbody#notebooksList td")).toList.map(_.text)
      notebookNames should contain ("My New Notebook")
    }
  }
}
