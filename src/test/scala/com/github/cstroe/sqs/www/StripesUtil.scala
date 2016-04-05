package com.github.cstroe.sqs.www

object StripesUtil {
  def host = "http://localhost:8080/"

  def getUrl(cls: Class[_]): String = {
    host + "/app/" + cls.getSimpleName.replace("ActionBean", "").toLowerCase
  }

  def getUrl(cls: Class[_], event: String): String = {
    getUrl(cls) + "/" + event
  }
}
