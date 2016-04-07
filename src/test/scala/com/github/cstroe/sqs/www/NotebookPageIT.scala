package com.github.cstroe.sqs.www

import org.scalatest._
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.HtmlUnit

class NotebookPageIT extends FunSpec with BeforeAndAfterEach with ShouldMatchers with HtmlUnit with Eventually {

  override def beforeEach {
    go to StripesUtil.getUrl(classOf[DbUnitActionBean])
    eventually {
      pageTitle shouldBe "View Notes"
    }
  }

  describe("The add notebook page") {
    def visitPage = {
      go to StripesUtil.getUrl(classOf[NotebookActionBean])

      eventually {
        pageTitle shouldBe "Add Notebook"
      }

      click on "notebook.name"
    }

    describe("when creating a notebook") {
      def createNotebook = {
        visitPage
        textField("notebook.name").value = "My New Notebook"
        submit

        eventually {
          pageTitle shouldBe "View Notes"
        }
      }

      it("should show it on the view page") {
        createNotebook

        val notebookNames = findAll(cssSelector("tbody#notebooksList td")).toList.map(_.text)
        notebookNames should contain("My New Notebook")
      }

      it("should not allow the user to create a notebook with the same name") {
        createNotebook
        visitPage

        textField("notebook.name").value = "My New Notebook"
        submit

        eventually {
          pageTitle shouldBe "Add Notebook"
        }

        val alert = find(cssSelector("div.alert-warning")).get.text
        alert should include ("A notebook already exists with that name")
      }
    }

    it("should not allow a user to create a notebook with a blank name") {
      visitPage
      submit

      eventually {
        pageTitle shouldBe "Add Notebook"
      }

      val alert = find(cssSelector("div.alert-warning")).get.text
      alert should include ("Notebook name must be specified")
    }
  }
}
