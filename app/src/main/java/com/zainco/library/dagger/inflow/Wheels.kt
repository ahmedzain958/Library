package com.zainco.daggerpractice.inflow

import javax.inject.Inject

class Wheels @Inject constructor(val tires: Tires, val rims: Rims)