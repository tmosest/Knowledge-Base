new Handler().postDelayed(new Runnable() {
  @Override
  public void run() {

    //Create an intent that will start the main activity.
    Intent mainIntent = new Intent(SplashActivity.this, MainMenuActivity.class);
    SplashActivity.this.startActivity(mainIntent);

    //Finish splash activity so user cant go back to it.
    SplashActivity.this.finish();

    //Apply splash exit (fade out) and main entry (fade in) animation transitions.
    overridePendingTransition(R.anim.mainfadein, R.anim.splashfadeout);
  }
}, 3000);
