package com.github.cstroe.sqs.hibernate

import org.scalatest.FlatSpec

class NotebookDtoSpec extends FlatSpec {
  val id = 1
  val name = "Notebookname"
  def group = new NotebookDto(id, name)

  "The NotebookDto constructor" should "accept valid input" in {
    val group = new NotebookDto(id, name)
    assert(group.getId == id)
    assert(group.getName == name)
  }

  it should "not accept a negative id" in {
    intercept[IllegalArgumentException] {
      new NotebookDto(-1, "some name")
    }
  }

  it should "not accept a null name" in {
    intercept[NullPointerException] {
      new NotebookDto(1, null)
    }
  }
}
