import com.desafio.domain.repositories.UserRepository
import com.example.data.db.UserDao
import com.example.data.db.UserEntity
import com.example.data.dto.UserDto
import com.example.data.repositories.UserRepositoryImpl
import com.nhaarman.mockitokotlin2.*
import com.picpay.data.network.PicPayApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private val api = mock<PicPayApi>()
    private val dao  = mock<UserDao>()
    private lateinit var repository: UserRepository

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Before
    fun setupRepository() {
        repository = UserRepositoryImpl(testDispatcher, api, dao)
    }

    @Test
    fun getUsers_returnUsers() = runBlockingTest {
        // given
        val user = UserEntity(2,"Teste", "ImgUrl", "Teste")
        dao.stub {
            onBlocking { getUsers() }.doReturn((arrayListOf(user)))
        }

        //when
        val value = repository.getUsers()

        // Then
        MatcherAssert.assertThat(value[0].name, CoreMatchers.`is`("Teste"))
    }

    @Test
    fun refresh_InsertiUserInCache() = runBlockingTest {
        // given
        val user = UserDto(2,"Teste", "ImgUrl", "Teste")
        val userEntity = UserEntity(2,"Teste", "ImgUrl", "Teste")
        api.stub {
            onBlocking { getUsers() }.doReturn((arrayListOf(user)))
        }

        //when
        repository.refresh()

        // Then
        verify(dao, times(1)).insertAll(arrayListOf(userEntity))
    }


}