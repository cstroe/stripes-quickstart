package com.github.cstroe.sqs.dao

import org.scalatest.FlatSpec

class NotebookDaoSpec extends FlatSpec {
  val id = 1
  val name = "Notebookname"
  def group = new NotebookDao(id, name)

  "The NotebookDao constructor" should "accept valid input" in {
    val group = new NotebookDao(id, name)
    assert(group.getId == id)
    assert(group.getName == name)
  }

  it should "not accept a negative id" in {
    intercept[IllegalArgumentException] {
      new NotebookDao(-1, "some name")
    }
  }

  it should "not accept a null name" in {
    intercept[NullPointerException] {
      new NotebookDao(1, null)
    }
  }
}
