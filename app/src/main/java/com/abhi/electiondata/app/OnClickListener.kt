package com.abhi.electiondata.app

import com.abhi.electiondata.model.User
import com.abhi.electiondata.model.VoterId

interface OnClickListener {
    fun onClickEvent(user: VoterId)
}