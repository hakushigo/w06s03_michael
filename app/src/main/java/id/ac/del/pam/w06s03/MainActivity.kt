package id.ac.del.pam.w06s03

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import id.ac.del.pam.w06s03.ShowDetail.ShowDetailActivity
import id.ac.del.pam.w06s03.ui.theme.W06s03Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            W06s03Theme {
                JetCoffeeApp()
            }
        }
    }
}

@Composable
fun JetCoffeeApp(modifier: Modifier = Modifier){
    Scaffold (
        bottomBar = { BottomBar() }
    ) {
        innerPadding -> Column (
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ){
            Banner()
            HomeSection(
                title = stringResource(id = R.string.section_category)
            ) {
                CategoryRow()
            }

            HomeSection(
                title = stringResource(id = R.string.section_favorite_menu)
            ) {
                MenuRow(Menus)
            }

            HomeSection(
                title = stringResource(id = R.string.section_best_seller_menu)
            ) {
                MenuRow(dummyBestSellerMenu)
            }


    }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier
){
    val context = LocalContext.current;

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(listMenu, key = {it.title}) {
            menu -> MenuItem(menu = menu, modifier = modifier.clickable {
            val detailIntent = Intent(context, ShowDetailActivity::class.java)

            detailIntent.putExtra("title", menu.title);
            detailIntent.putExtra("price", menu.price);
            detailIntent.putExtra("Image", menu.Image);

            context.startActivity(detailIntent)
        })
        }
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier){
    Box(modifier = modifier){
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        Search()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryRow(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(dummyCategory, key = {it.textCategory}) {
            Category -> CategoryItem(category = Category, modifier = modifier.clickable {
                val h = AlertDialog.Builder(context)
                .setTitle("This category doesn't exsits")
                .setMessage("sorry")
                    .setPositiveButton(android.R.string.yes, null)
                    .show()
            })
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = stringResource(id = R.string.menu_home),
                icon = Icons.Default.Home
            ),
            BottomBarItem(
                title = stringResource(id = R.string.menu_favorite),
                icon = Icons.Default.Favorite
            ),
            BottomBarItem(
                title = stringResource(id = R.string.menu_profile),
                icon = Icons.Default.AccountCircle
            ),
        )

        navigationItems.map {
            NavigationBarItem(selected = it.title == navigationItems[0].title, onClick = { /*TODO*/ }, icon = { Icon(imageVector = it.icon, contentDescription = it.title) })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryRowPreview()
{
    W06s03Theme {
        CategoryRow()
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JetCoffeeAppPreview(){
    W06s03Theme {
        JetCoffeeApp()
    }
}