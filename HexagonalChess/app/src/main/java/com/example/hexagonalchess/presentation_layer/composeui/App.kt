@file:Suppress("DEPRECATION")

package com.example.hexagonalchess.presentation_layer.composeui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.hexagonalchess.data_layer.chess_board_data.base.ChessboardData
import com.example.hexagonalchess.data_layer.chess_board_data.big.BigChessBoardData
import com.example.hexagonalchess.data_layer.chess_board_data.shafran.ShafranChessBoardData
import com.example.hexagonalchess.data_layer.chess_board_data.starchess.ShurikenBoardData
import com.example.hexagonalchess.data_layer.database.FireBaseDatabasePlayer
import com.example.hexagonalchess.data_layer.database.FirebaseRealtimeDatabaseGame
import com.example.hexagonalchess.domain_layer.BoardType
import com.example.hexagonalchess.domain_layer.GameMode
import com.example.hexagonalchess.domain_layer.PieceColor
import com.example.hexagonalchess.domain_layer.Route
import com.example.hexagonalchess.domain_layer.player.manager.PlayerNameSharedPref
import com.example.hexagonalchess.presentation_layer.composeui.gameplay.local.GameScreen
import com.example.hexagonalchess.presentation_layer.composeui.gameplay.online.MultiplayerGameScreen
import com.example.hexagonalchess.presentation_layer.viewmodel.BoardSelectionViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessBoardViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.ChessMultiPlayerViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.SettingViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.ShopViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.SignInViewModel
import com.example.hexagonalchess.presentation_layer.viewmodel.SignUpViewModel

@Composable
fun App(
    navController: NavHostController,
    playerNameSharedPref: PlayerNameSharedPref,
    context: Context,
    closeAppFunction:() -> Unit,
    playerName: String?
) {
    val viablePieceColor = listOf(PieceColor.WHITE,PieceColor.BLACK)

    val databaseForPlayer = FireBaseDatabasePlayer(context)
    val databaseGame = FirebaseRealtimeDatabaseGame(context)
    val startDestination = if (playerName.isNullOrBlank()) {
        Route.signUp
    } else {
        Route.main
    }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Route.main
        ) {
            MainMenu(
                navController = navController,
                playerName = playerNameSharedPref.getPlayerName().toString(),
                databasePlayer = databaseForPlayer,
                context = context,
                closeAppFunction
            )
        }
        composable(
            route = "${Route.game}/{gameMode}/{boardType}",
            arguments = listOf(
                navArgument("boardType") {
                    type = NavType.EnumType(BoardType::class.java)
                },
                navArgument("gameMode") {
                    type = NavType.EnumType(GameMode::class.java)
                }
            )
        ) {
            val boardType = it.arguments?.getSerializable("boardType") as BoardType
            val gameMode = it.arguments?.getSerializable("gameMode") as GameMode
            GameScreen(
                chessViewModel = ChessBoardViewModel(
                    allTiles = when(boardType) {
                        BoardType.DEFAULT -> ChessboardData().allTiles
                        BoardType.STAR_CHESS -> ShurikenBoardData().allTiles
                        BoardType.SHAFRAN -> ShafranChessBoardData().allTiles
                        BoardType.BIG -> BigChessBoardData().allTiles
                    }, boardType = boardType,
                    context = context,
                    gameMode = gameMode,
                    playerColor = viablePieceColor.random(),
                    databaseForPlayer
                ),
                context = context,
                boardType = boardType,
                navController = navController
            )
        }

        composable(
            route = Route.setting
        ) {
            SettingScreen(
                navController = navController,
                settingViewModel = SettingViewModel(
                    context = context,
                    navController = navController,
                    playerNameSharedPref = playerNameSharedPref,
                    databasePlayer = databaseForPlayer
                ),
                closeAppFunction = closeAppFunction,
                context = context
            )
        }

        composable(
            route = "${Route.boardSelection}/{gameMode}",
            arguments = listOf(
                navArgument("gameMode") {
                    type = NavType.EnumType(GameMode::class.java)
                }
            )
        ) {
            BoardSelectionScreen(
                boardSelectionViewModel = BoardSelectionViewModel(),
                navController = navController,
                gameMode = it.arguments?.getSerializable("gameMode") as GameMode
            )
        }

        composable(
            route = "${Route.loading}/{encodedRoute}",
            arguments = listOf(
                navArgument("encodedRoute") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.let { loadingArguments ->
                LoadingScreen(
                    navController = navController,
                    encodedRoute = loadingArguments.getString("encodedRoute").toString()
                )
            }
        }

        composable(
            route = Route.signUp
        ) {
            SignUpScreen(
                navController = navController,
                signUpViewModel = SignUpViewModel(databaseForPlayer)
            )
        }

        composable(
            route = Route.signIn
        ) {
            SignInScreen(
                navController = navController,
                signInViewModel = SignInViewModel(databaseForPlayer),
                context = context
            )
        }

        composable(
            route = "${Route.loadingOnline}/{boardType}",
            arguments = listOf(
                navArgument("boardType") {
                    type = NavType.EnumType(BoardType::class.java)
                }
            )
        ) {
            val boardType = it.arguments?.getSerializable("boardType") as BoardType
            LoadingScreenOnline(
                playerName = playerNameSharedPref.getPlayerName().toString(),
                databaseGame = databaseGame,
                board = when(boardType) {
                    BoardType.DEFAULT -> ChessboardData().allTiles
                    BoardType.STAR_CHESS -> ShurikenBoardData().allTiles
                    BoardType.SHAFRAN -> ShafranChessBoardData().allTiles
                    BoardType.BIG -> BigChessBoardData().allTiles
                },
                boardType = boardType,
                navController = navController,
                context = context
            )
        }

        composable(
            route = "${Route.online}/{boardType}",
            arguments = listOf(
                navArgument("boardType") {
                    type = NavType.EnumType(BoardType::class.java)
                }
            )
        ) {
            val boardType = it.arguments?.getSerializable("boardType") as BoardType
            MultiplayerGameScreen(
                navController = navController,
                boardType = boardType,
                chessBoardViewModel = ChessMultiPlayerViewModel(
                    database = databaseGame,
                    board = when(boardType) {
                        BoardType.DEFAULT -> ChessboardData().allTiles
                        BoardType.STAR_CHESS -> ShurikenBoardData().allTiles
                        BoardType.SHAFRAN -> ShafranChessBoardData().allTiles
                        BoardType.BIG -> BigChessBoardData().allTiles
                    },
                    boardType = boardType,
                    context = context
                ),
                context = context
            )
        }

        composable(route = Route.shop) {
            ShopScreen(
                shopViewModel = ShopViewModel(
                    databaseForPlayer,
                    playerName.toString(),
                    context
                ),
                navController = navController
            )
        }
    }
}