using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PauseGame : MonoBehaviour
{
    private bool isPaused = true;

    void Start()
    {
        //Pause();
    }

    void Update()
    {
        if (Input.GetKeyUp(KeyCode.P))
        {
            if (isPaused)
            {
                Unpause();
            }
            else
            {
                Pause();
            }
        }
    }

    void Pause()
    {
        Time.timeScale = 0;
        isPaused = true;
    }

    void Unpause()
    {
        Time.timeScale = 1;
        isPaused = false;
    }
}
