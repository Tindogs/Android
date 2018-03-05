package com.appvengers.business.interactors

import com.appvengers.business.models.Match
import com.appvengers.business.models.User

interface GetMatchesInteractor
{
    fun execute(user: User): List<Match>
}