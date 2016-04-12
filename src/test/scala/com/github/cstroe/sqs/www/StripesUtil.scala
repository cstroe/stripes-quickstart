package com.github.cstroe.sqs.www

import net.sourceforge.stripes.action.UrlBinding

object StripesUtil {
  def host = "http://localhost:8080/"

  def getUrl(cls: Class[_]): String = {
    val binding = cls.getAnnotation(classOf[UrlBinding])
    host + binding.value()
  }

  def getUrl(cls: Class[_], event: String): String = {
    val url = getUrl(cls)
    if(url.contains("{$event}")) {
      url.replace("{$event}", event)
    } else {
      url + "?" + event
    }
    url.replaceAll("/[{].*[}]", "")
  }
}
