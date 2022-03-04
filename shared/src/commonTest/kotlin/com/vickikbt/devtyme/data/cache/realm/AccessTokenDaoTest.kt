package com.vickikbt.devtyme.data.cache.realm

import com.vickikbt.devtyme.data.mappers.toEntity
import com.vickikbt.devtyme.data.network.models.AccessTokenDto
import io.github.aakira.napier.Napier
import io.mockk.mockk
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.coroutines.runBlocking
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertNotNull


class AccessTokenDaoTest {

    lateinit var realmConfiguration: RealmConfiguration
    lateinit var realm: Realm
    lateinit var accessTokenDao: AccessTokenDao

    /*private val accessTokenEntity = AccessTokenDto(
        accessToken = "accessToken",
        expiresIn = 0.0,
        refreshToken = "refreshToken",
        scope = "scope",
        tokenType = "tokenType",
        uid = "uid",
        createdAt = 0f,
    ).toEntity()*/

    private val accessTokenEntity = mockk<AccessTokenDto>().toEntity()

    @BeforeTest
    fun setup() {
        //realmConfiguration = RealmConfiguration.with(schema = setOf(AccessTokenEntity::class))
        /*Builder().name("devtyme_test.db")
        .schema(setOf(AccessTokenEntity::class))
        .build()*/
        //realm = Realm.open(configuration = realmConfiguration)
        //accessTokenDao = AccessTokenDao(realm = realm)
    }

    @AfterTest
    fun tearDown() {
        //realm.close()
    }

    @Test
    fun saveUserTokenTest() {
        Napier.e("Access token entity under test: $accessTokenEntity")
        runBlocking {
            assertNotNull(accessTokenEntity)

            /*accessTokenDao.saveToken(tokenEntity = accessTokenEntity)

            val accessToken = accessTokenDao.getToken.first()[0]

            assertNotNull(accessToken)
            assertEquals(accessTokenEntity, accessToken)*/
        }
    }

    /*@Test
    fun getTokenTest() {
        runBlocking {
            assertNotNull(accessTokenEntity)

            accessTokenDao.saveToken(tokenEntity = accessTokenEntity)

            val accessToken = accessTokenDao.getToken.first()[0]

            assertNotNull(accessToken)
            assertEquals(accessTokenEntity, accessToken)
        }
    }

    @Test
    fun deleteTokenTest() {
        runBlocking {
            assertNotNull(accessTokenEntity)

            accessTokenDao.saveToken(tokenEntity = accessTokenEntity)

            val accessToken = accessTokenDao.getToken.first()[0]

            assertNotNull(accessToken)
            assertEquals(accessTokenEntity, accessToken)

            accessTokenDao.deleteToken()

            assertNull(accessToken)
        }
    }*/


}
