package com.vladlen.dotaautochessguide.model.vo.guide

import com.vladlen.dotaautochessguide.model.Constants

class GuideItemVo(val title: String = Constants.EMPTY_STRING,
                  val image: String = Constants.EMPTY_STRING,
                  val desc: String = Constants.EMPTY_STRING,
                  val id: String = Constants.EMPTY_STRING) : IGuide