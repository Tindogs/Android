package com.appvengers.repository.db

interface DAOWritable<in T> {
    fun insert(element: T): Long

     /**
     * Actualiza el elemento con databaseID con los datos de elemento
     * Devuelve el número de registros afectados
     */
    fun update(element: T): Boolean
    /**
     * deletes the element passed from DB
     */
    fun delete(element: T): Boolean

    /**
     * delete the element with id from DB
     */
    fun delete(databaseID: Long): Boolean
    fun deleteAll(): Boolean
}

interface  DAOReadable<out T> {
    fun query(databaseID: Long): T?
    fun queryAll(): List<T>

}
interface DAOPersistable<T>: DAOReadable<T>, DAOWritable<T>