package com.appvengers.repository.db

interface DAOWritable<in T> {

    fun insert(element: T): String

    fun insert(list: List<T>): Boolean
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
    fun delete(databaseID: String): Boolean
    fun deleteAll(): Boolean
}

interface  DAOReadable<out T> {
    fun query(databaseID: String): T?
    fun queryAll(): List<T>
    fun queryAllWithId(id: String): List<T>

}
interface DAOPersistable<T>: DAOReadable<T>, DAOWritable<T>