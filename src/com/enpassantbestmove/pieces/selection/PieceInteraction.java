package com.enpassantbestmove.pieces.selection;

import com.enpassantbestmove.gui.board.BoardFactory;
import com.enpassantbestmove.gui.GUISounds;
import com.enpassantbestmove.gui.general.PlayMusic;
import com.enpassantbestmove.pieces.Piece;
import com.enpassantbestmove.pieces.placement.Castle;
import com.enpassantbestmove.pieces.placement.KillByEnPassant;
import com.enpassantbestmove.pieces.placement.RelocatePiece;

// controls what happens when tiles are pressed

public class PieceInteraction {
    private int selectedXCoord;
    private int selectedYCoord;
    private Piece currentSelectedPiece;
    private Piece previousSelectedPiece;

    public PieceInteraction(int xCoord, int yCoord) {
        this.selectedXCoord = xCoord;
        this.selectedYCoord = yCoord;
        previousSelectedPiece = new FindSelection().getPreviousSelection();
        currentSelectedPiece = new FindSelection().getSelectedPiece(xCoord, yCoord);
    }

    // returns SelectionCommands depending on the circumstances
    public SelectionCommands selectionLogic() {
        if (previousSelectedPiece == null) {
            if (currentSelectedPiece != null && currentSelectedPiece.getColor() == BoardFactory.getTurn())
                return SelectionCommands.SELECT;
            else
                return SelectionCommands.DO_NOTHING;
        } else {
            if (previousSelectedPiece.getXCoord() == selectedXCoord && previousSelectedPiece.getYCoord() == selectedYCoord) {
                return SelectionCommands.DESELECT;
            } else if (currentSelectedPiece == null) {
                if (previousSelectedPiece.canEnpassant(BoardFactory.createPieceLayoutCopy(), selectedXCoord, selectedYCoord))
                    return SelectionCommands.EN_PASSANT;
                else if (previousSelectedPiece.canMove(BoardFactory.createPieceLayoutCopy(), selectedXCoord, selectedYCoord))
                    return SelectionCommands.GOTO_DESELECT;
                else if (previousSelectedPiece.canCastle(BoardFactory.createPieceLayoutCopy(), selectedXCoord, selectedYCoord))
                    return SelectionCommands.CASTLE;
                else
                    return SelectionCommands.DESELECT;
            } else {
                if (currentSelectedPiece.getColor() == previousSelectedPiece.getColor()) {
                    return SelectionCommands.DESELECT_SELECT;
                } else {
                    if (previousSelectedPiece.canTake(BoardFactory.createPieceLayoutCopy(), selectedXCoord, selectedYCoord))
                        return SelectionCommands.GOTO_DESELECT;
                    else
                        return SelectionCommands.DESELECT;
                }
            }
        }
    }

    // controls what to do with the returned commands
    public void select () {
        var doWhat = selectionLogic();

        switch (doWhat) {
            case SELECT:
                Indicators.placeIndicators(currentSelectedPiece);
                PlayMusic.play(GUISounds.pieceSelectionSoundURL);
                break;

            case DESELECT:
                Deselect.deselectOne(previousSelectedPiece);
                break;

            case DESELECT_SELECT:
                Deselect.deselectOne(previousSelectedPiece);
                Indicators.placeIndicators(currentSelectedPiece);
                PlayMusic.play(GUISounds.pieceSelectionSoundURL);
                break;

            case GOTO_DESELECT:
                RelocatePiece.relocate(previousSelectedPiece, selectedXCoord, selectedYCoord);
                Deselect.deselectOne(previousSelectedPiece);
                BoardFactory.changeTurn(currentSelectedPiece, previousSelectedPiece);
                PlayMusic.play(GUISounds.piecePlacementSoundURL);
                break;

            case EN_PASSANT:
                KillByEnPassant.killAndUpdate(selectedXCoord, selectedYCoord);
                RelocatePiece.relocate(previousSelectedPiece, selectedXCoord, selectedYCoord);
                Deselect.deselectOne(previousSelectedPiece);
                BoardFactory.changeTurn(currentSelectedPiece, previousSelectedPiece);
                PlayMusic.play(GUISounds.piecePlacementSoundURL);
                break;

            case CASTLE:
                Castle.castle(previousSelectedPiece, selectedXCoord, selectedYCoord);
                RelocatePiece.relocate(previousSelectedPiece, selectedXCoord, selectedYCoord);
                Deselect.deselectOne(previousSelectedPiece);
                BoardFactory.changeTurn(currentSelectedPiece, previousSelectedPiece);
                PlayMusic.play(GUISounds.piecePlacementSoundURL);
        }
    }
}
