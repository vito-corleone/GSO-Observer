package bank.internettoegang;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import bank.bankieren.IBank;
import bank.bankieren.IRekening;
import bank.bankieren.Money;

import fontys.util.InvalidSessionException;
import fontys.util.NumberDoesntExistException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Bankiersessie extends UnicastRemoteObject implements
		IBankiersessie {

	private static final long serialVersionUID = 1L;
	private long laatsteAanroep;
	private int reknr;
	private IBank bank;
        private long geldigheidsduur = GELDIGHEIDSDUUR;

	public Bankiersessie(int reknr, IBank bank) throws RemoteException {
		laatsteAanroep = System.currentTimeMillis();
		this.reknr = reknr;
		this.bank = bank;	
	}

        /**
         * Ten behoeve van de unit test waarbij de geldigheid van een sessie kan worden getest is er gekozen om een SETTER voor de geldigheid
         * te maken zodat de tijd om te wachten kan worden beinvloed         * 
         * @Author Kamil Wasylkiewicz
         * @param geldigheidsduur de nieuwe geldiheidsduur
         */
        public void setGeldigheidsDuur(long geldigheidsduur){
            this.geldigheidsduur = geldigheidsduur;
        }
        
	public boolean isGeldig() {
		return System.currentTimeMillis() - laatsteAanroep < geldigheidsduur;
	}

	@Override
	public boolean maakOver(int bestemming, Money bedrag)
			throws NumberDoesntExistException, InvalidSessionException,
			RemoteException {
		
		updateLaatsteAanroep();
		
		if (reknr == bestemming)
			throw new RuntimeException(
					"source and destination must be different");
		if (!bedrag.isPositive())
			throw new RuntimeException("amount must be positive");
		
		return bank.maakOver(reknr, bestemming, bedrag);
	}

	private void updateLaatsteAanroep() throws InvalidSessionException {
		if (!isGeldig()) {
			throw new InvalidSessionException("session has been expired");
		}
		
		laatsteAanroep = System.currentTimeMillis();
	}

	@Override
	public IRekening getRekening() throws InvalidSessionException,
			RemoteException {

		updateLaatsteAanroep();
		
		return bank.getRekening(reknr);
	}

	@Override
	public void logUit() throws RemoteException {
		UnicastRemoteObject.unexportObject(this, true);
	}

}
