package com.nmihalek.diffcoverageexample.di

import com.nmihalek.diffcoverageexample.calculator.AdditionOperator
import com.nmihalek.diffcoverageexample.calculator.MultiplyOperator
import com.nmihalek.diffcoverageexample.calculator.Operator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface OperatorModule {
    @Binds
    @Multiplication
    fun provideMultiplication(operator: MultiplyOperator): Operator

    @Binds
    @Addition
    fun provideAddition(operator: AdditionOperator): Operator
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Multiplication

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Addition