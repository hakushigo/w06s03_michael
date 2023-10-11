package id.ac.del.pam.w06s03.ShowDetail

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.ac.del.pam.w06s03.BottomBar
import id.ac.del.pam.w06s03.R
import id.ac.del.pam.w06s03.ShowDetail.ui.theme.W06s03Theme

class ShowDetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            W06s03Theme {
                var extra = getIntent().getExtras()
                if (extra != null) {
                    extra.getString("title")
                        ?.let { ShowingDetail(title = it, imageID = extra.getInt("Image"), price = extra.getString("price")!!) }
                }
            }
        }
    }


    @Composable
    @Preview()
    fun PreviewShowingDetail(){
        ShowingDetail(title = stringResource(R.string.placeholder_search), imageID = R.drawable.menu2, price = "Rp. 10.000");
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowingDetail(
        modifier: Modifier = Modifier,
        title: String,
        imageID: Int,
        price: String
    ){
        Scaffold (
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Detail", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                    },
                    navigationIcon = {
                        /** TO DO */
                        IconButton(onClick = {
                            this.onBackPressed()
                        }) {
                            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "back")
                        }
                    },
                    modifier = Modifier
                )
            },
            bottomBar = { BottomBar() },
        ) {
                innerPadding -> Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(innerPadding)
                .padding(10.dp, 15.dp)
        ) {
            ElevatedCard (

                modifier = modifier
                    .fillMaxWidth()
                    .padding(5.dp, 15.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                )

            ) {

                Image(
                    painter = painterResource(id = imageID),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(8.dp, 0.dp))
                )

                Column (
                    modifier = Modifier.padding(15.dp, 20.dp)
                ) {
                    Text(text = title, fontSize = 32.sp,
                        fontWeight = FontWeight.Bold ,
                        textAlign = TextAlign.Justify ,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 0.dp, 0.dp, 20.dp))

                    Row (
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(price, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                        Button(onClick = { /* nothing to do */ } ) {
                            Text(text = "Purchase")
                        }
                    }
                }

            }
        }

        }
    }
}
