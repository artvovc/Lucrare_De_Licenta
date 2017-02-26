package com.university.nn.kotlinbased.db.utils

import com.university.nn.kotlinbased.db.repository.impl.FeedDaoImpl
import org.apache.log4j.Logger

/**
 * Created by dsdmsa on 2/26/17.
 */


fun FeedDaoImpl.log(value:String){
    Logger.getLogger(FeedDaoImpl::class.java).warn(value)
}